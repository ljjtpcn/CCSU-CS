LIBRARY IEEE;
USE IEEE.STD_LOGIC_1164.ALL;
USE IEEE.STD_LOGIC_UNSIGNED.ALL;
--LIBRARY work;
LIBRARY lpm;
USE lpm.lpm_components.all;

ENTITY datapath IS
port(
	reset:in std_logic; --时钟 
	clk:in std_logic; --时钟 
	k:in std_logic_vector(7 downto 0);--段显示控制（abcdefg） 
	seg:out std_logic_vector(7 downto 0);--段显示控制（abcdefg） 
	y:out std_logic_vector(7 downto 0); --数码管地址选择控制 
	dout:inout std_logic_vector(7 downto 0) --数码管地址选择控制 
);
END datapath;

ARCHITECTURE Structural OF datapath IS

--ATTRIBUTE black_box : BOOLEAN;
--nATTRIBUTE noopt : BOOLEAN;

--COMPONENT lpm_ram_io_0
--	PORT(memenab : IN STD_LOGIC;
--		 inclock : IN STD_LOGIC;
--		 we : IN STD_LOGIC;
--		 outenab : IN STD_LOGIC;
--		 address : IN STD_LOGIC_VECTOR(7 DOWNTO 0)
--);
--END COMPONENT;
--ATTRIBUTE black_box OF lpm_ram_io_0: COMPONENT IS true;
--ATTRIBUTE noopt OF lpm_ram_io_0: COMPONENT IS true;

COMPONENT lpm_ram_io0 IS
PORT
(
address : IN STD_LOGIC_VECTOR (7 DOWNTO 0);
we : IN STD_LOGIC  := '1';
inclock : IN STD_LOGIC ;
outclock : IN STD_LOGIC ;
outenab : IN STD_LOGIC  := '1';
dio : INOUT STD_LOGIC_VECTOR (7 DOWNTO 0)
);
END COMPONENT;

COMPONENT exp_r_alu is
port(	clk,reset                    :IN STD_LOGIC;
		sw_bus,r4_bus,r5_bus,alu_bus,memen :IN STD_LOGIC;
		lddr1,lddr2,ldr4,ldr5        :IN STD_LOGIC;
		m,cn                         :IN STD_LOGIC;
		s                            :IN STD_LOGIC_VECTOR(3 DOWNTO 0);
		k                            :IN STD_LOGIC_VECTOR(7 DOWNTO 0);
		d                            :INOUT STD_LOGIC_VECTOR(7 DOWNTO 0)
 );
END COMPONENT;

 
COMPONENT sw_pc_ar IS
PORT(  clk,pcclr,pcld,pcen,reset:IN STD_LOGIC;
	   sw_bus,pc_bus,ldar     :IN STD_LOGIC;
	   inputd :IN    STD_LOGIC_VECTOR(7 DOWNTO 0);
	   arout  :OUT   STD_LOGIC_VECTOR(7 DOWNTO 0);
	   d      :INOUT STD_LOGIC_VECTOR(7 DOWNTO 0) 
);
END COMPONENT;



COMPONENT display is
port(
	clk:in std_logic;--------------------------------时钟信号
	in84:in std_logic_vector(7 downto 0);--------------数码管
	in83:in std_logic_vector(7 downto 0);--------------数码管
	in82:in std_logic_vector(7 downto 0);--------------数码管
	in81:in std_logic_vector(7 downto 0);--------------数码管
	seg:out std_logic_vector(7 downto 0);--------------数码管
	sel:out std_logic_vector(7 downto 0) -------------段位
);
END COMPONENT;


COMPONENT fsm is
port(
	reset:in std_logic;--------------------------------时钟信号
	clk:in std_logic;--------------------------------时钟信号
	clkcnt:out std_logic;--------------------------------时钟信号
	clkfresh:out std_logic;--------------------------------时钟信号
	test:out std_logic_vector(7 downto 0);
	mcode:out std_logic_vector(21 downto 0)
);
END COMPONENT;

--21bus16ld11op5pc2m
--21sw-20r4-19r5-18alu-17pc|16r1-15r2-14r4-13r5-12ar|11m-10cn-9s38s27s16s0|5pcclr-4pcld-3pcen|2memen-1mw-0mr
SIGNAL smcode: std_logic_vector(21 downto 0); 
SIGNAL clkcnt: std_logic; 
SIGNAL clkfresh: std_logic; 
SIGNAL sar: std_logic_vector(7 downto 0); 
SIGNAL sdbus: std_logic_vector(7 downto 0); 
SIGNAL smibus: std_logic_vector(7 downto 0); 
SIGNAL test: std_logic_vector(7 downto 0); 

SIGNAL clkon:STD_LOGIC; 
begin

PROCESS(clkcnt) --
BEGIN 
    IF (reset ='0') THEN
       clkon<='1';
	ELSIF clkcnt='1' AND clkcnt'event THEN 
		clkon<=not clkon; 
	END IF; 
end process; 



U0: fsm PORT MAP (  
	reset => reset,
	   clk   =>clk,
       clkcnt => clkcnt,
       clkfresh => clkfresh,
       test =>test,
       mcode  => smcode
);

U1: sw_pc_ar PORT MAP (
       clk =>clkcnt,
       reset =>reset,
       pcclr =>smcode( 5) ,
       pcld=>smcode( 4),
       pcen=>smcode( 3),
	   sw_bus=>smcode( 21),
	   pc_bus=>smcode( 17),
	   ldar=>smcode( 12),
	   inputd =>k,
	   arout =>sar,
	   d=>sdbus 
);

--U2 : lpm_ram_io_0
--PORT MAP(memenab => '1',
--		 inclock => clkcnt,
--		 we => smcode( 1),
--		 outenab => smcode( 0),
--		 address => sar,
--		 dio => smbus);

U2: lpm_ram_io0  
PORT MAP
(
address =>sar,
we => smcode( 1),
inclock  =>    clkcnt,  --and (not clkon)
outclock  =>   clkcnt,  -- and (not clkon),
outenab =>  smcode( 0),
dio =>sdbus
);


U3: exp_r_alu PORT MAP (
	    clk   =>clkcnt,
	    reset =>reset,
	    memen =>smcode( 0),
		sw_bus=>smcode( 21),
		r4_bus=>smcode( 20),
		r5_bus=>smcode( 19),
		alu_bus =>smcode( 18),
		lddr1 =>smcode( 16),
		lddr2=>smcode( 15),
		ldr4=>smcode( 14),
		ldr5  =>smcode( 13),
		m =>smcode( 11)	,
		cn   =>smcode(10),
		s   =>smcode(9 downto 6),
		k   =>k,
		d  =>sdbus
);


U4: display PORT MAP (
	clk  => clkfresh,
	in84 => test,
	in83 => sar,
	in82 => "00000000",
	in81 => sdbus,
	seg  => seg,
	sel  => y
);


end Structural;