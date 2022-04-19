library ieee;
use ieee.std_logic_1164.all;
entity vhdls is
port( 
	clr,s0,s1,clk:in std_logic;
	p:in std_logic_vector(3 downto 0);
	q:out std_logic_vector(3 downto 0)
);
end vhdls;

architecture behav of vhdls is
signal qs:std_logic_vector(3 downto 0);
begin 
process(clr,s0,s1,clk) is
begin
if(clr='0')then
	qs<="0000";
elsif(clk'event and clk='1')then
	if(s1='1')and(s0='1')then
		qs<=p;
	elsif(s1='0')and(s0='1')then
		qs(3)<=qs(0);
		qs(2 downto 0)<=qs(3 downto 1);
	elsif(s1='1')and(s0='0')then
		qs(0)<=qs(3);
		qs(3 downto 1)<=qs(2 downto 0);
	end if;
end if;	
q<=qs;
end process;
end behav;
