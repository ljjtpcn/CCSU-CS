LIBRARY IEEE;
USE IEEE.STD_LOGIC_1164.ALL;
USE IEEE.STD_LOGIC_UNSIGNED.ALL;
ENTITY RS_clk IS
PORT( s,r:IN std_logic;
q,not_q:out std_logic);
END RS_clk;
ARCHITECTURE RS_clk_arch OF RS_clk IS
signal q1,nq1: std_logic;
BEGIN
process(r,s)
begin
if (s='0'and r='1')then
	q1<='0';
	nq1<='1';
elsif (s='1' and r='0') then 
q1<='1';
nq1<='0';
elsif (s='1' and r='1') then 
q1<='X';
nq1<='X';
elsif (s='0' and r='0') then 
q1<=q1; 
nq1<=nq1;
end if;
end process;
q<=q1;
not_q<=nq1;
END RS_clk_arch;
