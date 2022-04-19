LIBRARY ieee;
USE ieee.std_logic_1164.ALL;
USE ieee.std_logic_unsigned.ALL;

ENTITY fsmctrl is 
    generic(N:integer:=50000; X:integer:=10 ; Y:integer:=100); -- pin21 100Khz N for clk input freq
port(
	reset:in std_logic;--------------------------------复位reset 
	clk:in std_logic;--------------------------------时钟信号
	clkcnt:out std_logic;--------------------------------时钟信号
	clkfresh:out std_logic;--------------------------------时钟信号
	test:out std_logic_vector(7 downto 0);--------------数码管
	en_idec:out std_logic;--------------------------------时钟信号
	opcode:in std_logic_vector(3 downto 0);--------------数码管
	oprdata:in std_logic_vector(3 downto 0);--------------数码管
	
	mcode:out std_logic_vector(21 downto 0)--------------数码管
);
END fsmctrl;


Architecture RTL of fsmctrl is

TYPE State_type IS (A, B, C, D,E,F,H);  -- 定义状态
SIGNAL State : State_Type;    -- 创建信号 
SIGNAL stest: std_logic_vector(7 downto 0);--------------数码管
signal ctick,clk_state,clk_fresh:std_logic;
BEGIN 

-- 产生2hz clk2hz
PROCESS(clk) --产生2hz信号 tick
variable cnt1 : INTEGER RANGE 0 TO N -1; 
BEGIN 
    IF clk='1' AND clk'event THEN 
		IF cnt1=N -1 THEN 
			cnt1:=0; 
		ELSE 
			IF 	cnt1<N/2 THEN 
				ctick<='1'; 
			ELSE 
				ctick<='0';
			END IF; 
			cnt1:=cnt1+1; 
		END IF; 
	END IF; 
end process; 

PROCESS(ctick) --产生1hz信号 clk_state
BEGIN 
   IF (reset ='0') THEN
       clk_state<='1';
	ELSIF ctick='1' AND ctick'event THEN 
		clk_state<=not clk_state; 
	END IF; 
end process; 


-- 产生10khz clk10khz
PROCESS(clk) --产生10Khz信号 
variable cnt1 : INTEGER RANGE 0 TO X -1; 
BEGIN 
	IF rising_edge(clk) THEN 
		IF cnt1=X -1 THEN 
			cnt1:=0; 
		ELSE 
			IF 	cnt1<X/2 THEN 
				clk_fresh<='1'; 
			ELSE 
				clk_fresh<='0'; 
			END IF; 
			cnt1:=cnt1+1; 
		END IF; 
	END IF; 
end process; 

  PROCESS (clk_state, reset,opcode) 
  CONSTANT LDA:std_logic_vector(3 downto 0):="0010" ;-- LDA:=0010
  CONSTANT ADD:std_logic_vector(3 downto 0):="1100" ; -- ADD:=1100 
  CONSTANT YAND:std_logic_vector(3 downto 0):="1110" ; -- YAND:=1110
  CONSTANT JMP:std_logic_vector(3 downto 0):="0001" ; -- JMP:=0001
  CONSTANT STA:std_logic_vector(3 downto 0):="0011" ; -- STA :=0011
  CONSTANT INC:std_logic_vector(3 downto 0):="0100" ; -- INC :=0011
  CONSTANT SUB:std_logic_vector(3 downto 0):="0110" ; -- ADD:=1100
  variable vmcode: std_logic_vector(21 downto 0);
  variable ven_idec: std_logic;
  BEGIN 
--21sw-20r4-19r5-18alu-17pc|16r1-15r2-14r4-13r5-12ar|11m-10cn-9s38s27s16s0|5pcclr-4pcld-3pcen|2memen-1mw-0mr
--   x_busLD...运算器P Cmem 
--      a            c
--   srrlprrrra csssslleewr
--   w45uc1245rmn3210rdnnrd
--  "1111100000000000100100"
--  "1111100001000000100101"
    If (reset = '0') THEN            -- 复位reset，复位状态为A
		State <= A;
		vmcode :="1111100000000000100100";--  k=>PC
		ven_idec:='0';--清译码标记
		stest <="00000000";
    ELSIF rising_edge(clk_state) THEN   
		ven_idec:='0';
		vmcode :="1111100000000000100100";
		CASE State IS
			WHEN A => 
				vmcode :="1111000001000000111100";--  PC=>AR,PC++
				stest <="00000001";
				State <= B; 

			WHEN B => 
				vmcode :="1111100100000000100101";--  M=>r4(r4指令寄存器)
				ven_idec:='1';     --设置译码标记
				stest <="00000010";
				State <= C; 
			WHEN C => 
				CASE opcode IS
				when  LDA => vmcode :="1111000001000000111100";--  PC=>AR,PC++
				when  ADD => vmcode :="1111000001000000111100";--  PC=>AR,PC++
				when YAND => vmcode :="1111000001000000111100";-- PC=>AR,PC++
				when  JMP => vmcode :="1111000001000000111100";--  PC=>AR,PC++
				when  STA => vmcode :="1111000001000000111100";-- PC=>AR,PC++
				when  SUB => vmcode :="1111000001000000111100";--  PC=>AR,PC++
				WHEN others =>NULL;
				END CASE;
				stest <="00000011";
				State <= D; 
			WHEN D => 
				CASE opcode IS
				when LDA => vmcode :="1111100001000000100101";--  M=>ar
				when STA => vmcode :="1111100001000000100101";--  M=>ar
				when SUB => vmcode :="1111100001000000100101";--  M=>ar
				when ADD => vmcode :="1111100001000000100101";--  M=>ar
				when YAND => vmcode :="1111100001000000100101";-- M=>ar
				when JMP => vmcode :="1111100000000000101101";--  M=>PC
				WHEN others =>NULL;
				END CASE;
				stest <="00000100";
				State <= E; 
		WHEN E => 
				CASE opcode IS
				when LDA => vmcode :="1111100010000000100101";--  M=>r5
				when STA => vmcode :="1101100000000000100110";--  r5=>M
				when ADD => vmcode :="1111101000000000100101";--  M=>r2
				when SUB => vmcode :="1111101000000000100101";--  M=>r2
				when YAND => vmcode :="1111101000000000100101";-- M=>r2
				 
				WHEN others =>NULL;
				END CASE;
				stest <="00000101";
				State <= F; 
		
			WHEN F => 
				CASE opcode IS
				 
				when ADD => vmcode :="1101110000000000100101";--  r5=>r1
				when YAND => vmcode :="1111101000000000100101";--  r5=>r1
				when SUB => vmcode := "1101110000000000100101";--  r5=>r1
				WHEN others =>NULL;
				END CASE;
				stest <="00000110";
				State <= H; 
			
			WHEN H=> 
				CASE opcode IS
				when ADD => vmcode :="1110100010011001100100";--  r1+r2->r5
				when YAND => vmcode :="1110100100001011100100";--  r1 and r2->r5
				when SUB => vmcode :="1110100010000110100100";--  r1-r2->r5
				WHEN others =>NULL;
				END CASE;
				stest <="00000111";
				State <= A; 

			 
			
			WHEN others =>NULL;
		END CASE; 
    END IF; 
    mcode <=vmcode;
    en_idec <=ven_idec;
END PROCESS;
test <=stest;
clkcnt <= ctick;
clkfresh<=clk_fresh;
END rtl;

 
