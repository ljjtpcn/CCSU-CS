library ieee;
use ieee.std_logic_1164.all;
entity d_chufaqi is
port(	Clk:in STD_LOGIC;
	d:in STD_LOGIC;
	qb:out STD_LOGIC;
	q:out STD_LOGIC);
end	d_chufaqi;
architecture d_chufaqi_arch of d_chufaqi is
begin
	process(Clk,d)
	begin 
		if(Clk = '1'and Clk'event)then
			q <= d;
			qb <= not d;
		end if;
	end process;
end d_chufaqi_arch;
