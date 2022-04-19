library ieee;
use ieee.std_logic_1164.all;
entity adder is 
PORT (IN_a,IN_b,IN_c: IN STD_LOGIC;
s, ci: OUT STD_LOGIC);
end adder;
         
architecture one of adder is 
component decoder3_8              
PORT (i: in STD_LOGIC_vector(2 downto 0);      
y: OUT STD_LOGIC_VECTOR(7 downto 0));
END COMPONENT;

component ls20             
PORT (a,b,c,d: IN STD_LOGIC;       
out_y: OUT STD_LOGIC);
END component;
SIGNAL z1,x1,c1,v1,b1,n1,m1: STD_LOGIC;
BEGIN
U1: decoder3_8 PORT MAP (i(0)=>IN_a, i(1) =>IN_b,i(2)=>IN_c,    
Y(1)=>z1,Y(2)=>x1,Y(4)=>c1,Y(7)=>v1,Y(3)=>b1,Y(5)=>n1,Y(6)=>m1);  
U2:ls20 port map(a=>z1,b=>x1,c=>c1,d=>v1,out_y=>s);
U3:ls20 port map(a=>b1,b=>n1,c=>m1,d=>v1,out_y=>ci);
END one;