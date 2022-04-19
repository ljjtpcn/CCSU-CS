library ieee;
use ieee.std_logic_1164.all;

entity ls20 is
port(a,b,c,d:in std_logic;
out_y:out std_logic);
end ls20;

architecture Dataflow of ls20 IS
begin
out_y<=(not a or not b or not c or not d);
end Dataflow;