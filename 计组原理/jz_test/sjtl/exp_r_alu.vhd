library ieee;
use ieee.std_logic_1164.all;
use ieee.std_logic_unsigned.all;
entity exp_r_alu is
port(	clk                          :in std_logic;
		sw_bus,r4_bus,r5_bus,alu_bus :in std_logic;
		lddr1,lddr2,ldr4,ldr5        :in std_logic;
		m,cn                         :in std_logic;
		s                            :in std_logic_vector(3 downto 0);
		k                            :in std_logic_vector(7 downto 0);
		d                            :inout std_logic_vector(7 downto 0) );
end exp_r_alu;
architecture rtl of exp_r_alu is
signal dr1,dr2,r4,r5,aluout,bus_reg:std_logic_vector(7 downto 0);
signal sel:std_logic_vector(5 downto 0);
begin
ldreg:process(clk,lddr1,lddr2,ldr4,ldr5,bus_reg)
	begin
	if clk'event and clk='1' then
		if lddr1='1' then dr1<=bus_reg;
	    elsif lddr2='1' then dr2<=bus_reg;
	    elsif ldr4='1' then r4<=bus_reg;
	    elsif ldr5='1' then r5<=bus_reg;
	    end if;
	end if;
	end process;
alu:process(m,cn,s,dr1,dr2,sel,aluout)
   begin
   sel<=m & cn & s;
   case sel is
	when "000000" => aluout<=dr1+1;
	when "010000" => aluout<=dr1;
	when "100000" => aluout<=not dr1;
	when "000001" => aluout<=(dr1 or dr2)+1;
	when "010001" => aluout<=dr1 or dr2;
	when "100001" => aluout<=not(dr1 or dr2);
	when "000010" => aluout<=(dr1 or (not dr2))+1;
	when "010010" => aluout<=dr1 or (not dr2);
	when "100010" => aluout<=(not dr1)and dr2;
	when "000011" => aluout<=x"00";
	when "010011" => aluout<=aluout-1;
	when "100011" => aluout<=x"00";
	when "000100" => aluout<=dr1+(dr1 and (not dr2))+1;
	when "010100" => aluout<=dr1+(dr1 and (not dr2));
	when "100100" => aluout<=not (dr1 and dr2);
	when "000101" => aluout<=((dr1 or dr2 or dr1) and dr2) or x"01";
	when "010101" => aluout<=(dr1 or dr2)+(dr1 and(not dr2));
	when "100101" => aluout<=not dr2;
	when "000110" => aluout<=dr1-dr2;
	when "010110" => aluout<=dr1-dr2-1;
	when "100110" => aluout<=dr1 xor dr2;
	when "000111" => aluout<=dr1 and(not dr2);
	when "010111" => aluout<=(dr1 and (not dr2))-1;
	when "100111" => aluout<=dr1 and(not dr2);
	when "001000" => aluout<=dr1+(dr1 and dr2)+1;
	when "011000" => aluout<=dr1+(dr1 and dr2);
	when "101000" => aluout<=(not dr1)or dr2;
	when "001001" => aluout<=dr1+dr2+1;
	when "011001" => aluout<=dr1+dr2;
  
	when "101001" => aluout<=dr1 xnor dr2;
	when "001010" => aluout<=(dr1 or(not dr2))+(dr1 and dr2)+1;
	when "011010" => aluout<=(dr1 or(not dr2))+(dr1 and dr2);
	when "101010" => aluout<=dr2;
	when "001011" => aluout<=dr1 and dr2;
	when "011011" => aluout<=(dr1 and dr2)-1;
	when "101011" => aluout<=dr1 and dr2;
	when "001100" => aluout<=dr1+dr1+1;
	when "011100" => aluout<=dr1 or dr1;
	when "101100" => aluout<=x"01";
	when "001101" => aluout<=(dr1 or dr2)+dr1+1;
	when "011101" => aluout<=(dr1 or dr2)+dr1;
	when "101101" => aluout<=dr1 or(not dr2);
	when "001110" => aluout<=(dr1 or (not dr2))+dr1+1;
	when "011110" => aluout<=(dr1 or (not dr2))+dr1;
	when "101110" => aluout<=dr1 or dr2;
	when "001111" => aluout<=dr1;
	when "011111" => aluout<=dr1-1;
	when "101111" => aluout<=dr1;
	when others   => aluout<=x"ff";
end case;
end process;
bus_reg<=k      when sw_bus='0'  else
		 r4     when r4_bus='0'  else
		 r5     when r5_bus='0'  else
		 aluout  when alu_bus='0' else 
		d;
d<=bus_reg when (sw_bus='0' or r4_bus='0' or r5_bus='0' or alu_bus='0') else
   (others=>'Z');	
end rtl;