library ieee;
use ieee.std_logic_1164.all;
use ieee.std_logic_unsigned.all;

entity display is
port(
	clk:in std_logic;--------------------------------时钟信号
	in84:in std_logic_vector(7 downto 0);--------------数码管
	in83:in std_logic_vector(7 downto 0);--------------数码管
	in82:in std_logic_vector(7 downto 0);--------------数码管
	in81:in std_logic_vector(7 downto 0);--------------数码管
	seg:out std_logic_vector(7 downto 0);--------------数码管
	sel:out std_logic_vector(7 downto 0) -------------段位
);
end display;

architecture rtl of display is
signal cnt8:integer range 0 to 7;---------------计数
signal pos:integer range 0 to 7;---------------计数
signal tubein4:std_logic_vector(3 downto 0);--------------数码管

begin
-- 8进制计数器cnt8
process(clk) --
       VARIABLE tmpcnt :integer range 0 to 7; 
       begin
       if rising_edge(clk) then
         if (tmpcnt=7) then
             tmpcnt:=0;
         else 
             tmpcnt:=tmpcnt+1;
         end if;
       end if;
       cnt8<=tmpcnt;
end process;

-- 
-- 
process(cnt8)------------------只是显示在某种状态下的段位显示，不参与计算
begin
case cnt8 is
when 7 => pos <=7;sel <="01111111";------------cnt是1时，q显示0,依次选通s
when 6 => pos <=6;sel <="10111111";
when 5 => pos <=5;sel <="11011111";
when 4 => pos <=4;sel <="11101111";
when 3 => pos <=3;sel <="11110111";
when 2 => pos <=2;sel <="11111011";
when 1 => pos <=1;sel <="11111101";
when 0 => pos <=0;sel <="11111110";
when others => null;
end case;
end process;

process(tubein4)------------------只是显示在某种状态下的段位显示，不参与计算
begin
case tubein4 is
when "0000" => seg<="11111100";
when "0001"	=> seg<="01100000";
when "0010"	=> seg<="11011010"; 
when "0011"	=> seg<="11110010"; 
when "0100"	=> seg<="01100110"; 
when "0101"	=> seg<="10110110"; 
when "0110"	=> seg<="10111110"; 
when "0111"	=> seg<="11100000"; 
when "1000"	=> seg<="11111110"; 
when "1001"	=> seg<="11110110"; 
when "1010"	=> seg<="11101110";
when "1011"	=> seg<="00111110"; 
when "1100"	=> seg<="10011100"; 
when "1101"	=> seg<="01111010"; 
when "1110"	=> seg<="10011110"; 
when "1111"	=> seg<="10001110"; 
when others => null;
end case;
end process;


process(pos)------------------只是显示在某种状态下的段位显示，不参与计算
begin
case pos is
when 0 => tubein4(3 downto 0)<=in81(3 downto 0);
when 1 => tubein4(3 downto 0)<=in81(7 downto 4);
when 2 => tubein4(3 downto 0)<=in82(3 downto 0);
when 3 => tubein4(3 downto 0)<=in82(7 downto 4);
when 4 => tubein4(3 downto 0)<=in83(3 downto 0);
when 5 => tubein4(3 downto 0)<=in83(7 downto 4);
when 6 => tubein4(3 downto 0)<=in84(3 downto 0);
when 7 => tubein4(3 downto 0)<=in84(7 downto 4);
when others => null;
end case;
end process;

----------------------------------------------------------
end rtl;
