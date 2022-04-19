--以下是一位全加器结构描述的顶层设计
LIBRARY IEEE;
USE IEEE.STD_LOGIC_1164.ALL;
--以下是一位全加器实体描述
ENTITY full_adder IS
PORT (a, b, ci: IN STD_LOGIC;
         s, co: OUT STD_LOGIC);
END full_adder;
--以下是一位全加器结构体描述
ARCHITECTURE Structural OF full_adder IS
COMPONENT H_adder              --半加器部件声明
PORT (in_a, in_b    : IN STD_LOGIC;
      out_s, out_co : OUT STD_LOGIC);
END COMPONENT;
COMPONENT Or_2                 --或门部件声明
PORT (x, y: IN STD_LOGIC;
         z: OUT STD_LOGIC);
END COMPONENT;
SIGNAL u, v, w: STD_LOGIC; --定义内部三条连线
BEGIN
U1: H_adder PORT MAP (in_a=>a, in_b =>b,  
             out_s=>v, out_co=>u);  --端口映射
U2: H_adder PORT MAP (in_a=>v, in_b=>ci, 
             out_s=>s, out_co=>w);
U3: Or_2 PORT MAP (x => u, y => w, z=>co);
END Structural;