INSERT INTO Task (ID, Name, StartNodeName, EndNodeName) VALUES (1, 'Task =)', 'a', 'b');

INSERT INTO Connection (ID, StartNodeName, EndNodeName, Delay, Task_ID) VALUES (1, 'a', 'e', 2, 1);
INSERT INTO Connection (ID, StartNodeName, EndNodeName, Delay, Task_ID) VALUES (2, 'e', 'b', '2', 1);
INSERT INTO Connection (ID, StartNodeName, EndNodeName, Delay, Task_ID) VALUES (3, 'a', 'c', '0', 1);
INSERT INTO Connection (ID, StartNodeName, EndNodeName, Delay, Task_ID) VALUES (4, 'c', 'd', '8', 1);
INSERT INTO Connection (ID, StartNodeName, EndNodeName, Delay, Task_ID) VALUES (5, 'c', 'd', '8', 1);
INSERT INTO Connection (ID, StartNodeName, EndNodeName, Delay, Task_ID) VALUES (6, 'd', 'b', '0', 1);

INSERT INTO Connection (ID, StartNodeName, EndNodeName, Delay, Solution_ID) VALUES (7, 'a', 'b', '2', 1);

INSERT INTO Solution (ID, Task_ID) VALUES (1, 1);