--以下是或门描述
LIBRARY IEEE;
USE IEEE.STD_LOGIC_1164.ALL;
--以下是或门实体描述
ENTITY Or_2 IS
PORT (x, y: IN STD_LOGIC;
         z: OUT STD_LOGIC);
END Or_2;
--以下是或门结构体行为描述
ARCHITECTURE behavioral OF Or_2 IS
BEGIN
     Z<= '1' WHEN (x= '0' AND y= '1') ELSE
            '1' WHEN (x= '1' AND y= '0') ELSE
            '1' WHEN (x= '1' AND y= '1') ELSE
            '0';
END behavioral;