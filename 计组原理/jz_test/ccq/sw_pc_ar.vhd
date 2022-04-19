library ieee;
use ieee.std_logic_1164.all;
use ieee.std_logic_unsigned.all;
entity sw_pc_ar is
port(  clk_cdu,pcclr,pcld,pcen   :in std_logic;
	   sw_bus,pc_bus,ldar     :in std_logic;
	   inputd  :in    std_logic_vector(7 downto 0);
	   arout   :out   std_logic_vector(7 downto 0);
	   d      :inout  std_logic_vector(7 downto 0) );
end sw_pc_ar;
architecture rtl of sw_pc_ar is
signal pc,ar,bus_reg:std_logic_vector(7 downto 0);
begin
seq1:process(clk_cdu,ldar,bus_reg)
	begin
	if clk_cdu'event and clk_cdu='1' then
		if ldar='1' then ar<=bus_reg;
	    end if;
	end if;
	end process;
seq2:process(clk_cdu,pcclr,pcld,pcen,bus_reg)
	begin
	if pcclr='0' then pc<=(others=>'0');
	elsif clk_cdu'event and clk_cdu='1' then
		if (pcld='0' and pcen='1') then pc<=bus_reg;
	    elsif (pcld='1' and pcen='1') then pc<=pc+1;
	    end if;
	end if;
	end process;	
bus_reg<=inputd when sw_bus='0'  else
		 pc     when pc_bus='0'  else
		 d;
d<=bus_reg when (sw_bus='0' or pc_bus='0') else
   (others=>'Z');
arout<=ar;	   
end rtl;	   
