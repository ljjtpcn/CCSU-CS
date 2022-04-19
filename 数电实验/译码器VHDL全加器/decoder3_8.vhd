library ieee;
use ieee.std_logic_1164.all;

entity decoder3_8 is
port
(i:in std_logic_vector(2 downto 0);
y:out std_logic_vector(7 downto 0));
end decoder3_8;

architecture func of decoder3_8 is
begin
process(i)
begin
y<="11111111";
case i is
when"000"=>y(0)<='0';
when"001"=>y(1)<='0';
when"010"=>y(2)<='0';
when"011"=>y(3)<='0';
when"100"=>y(4)<='0';
when"101"=>y(5)<='0';
when"110"=>y(6)<='0';
when"111"=>y(7)<='0';
when others =>y<="11111111";
end case;
end process;
end architecture;