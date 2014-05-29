INSERT INTO `person` (`personid`,`dateofbirth`, `firstname`, `lastname`, `password`, `username`) VALUES ('1','1999-11-11', 'Charlie', 'Sheen', 'qwer', 'charlie');
INSERT INTO `person` (`personid`,`dateofbirth`, `firstname`, `lastname`, `password`, `username`) VALUES ('2','1971-11-11', 'Emma', 'Watson', 'qwer', 'emmawat');
INSERT INTO `person` (`personid`,`dateofbirth`, `firstname`, `lastname`, `password`, `username`) VALUES ('3','1981-11-11', 'Virat', 'Bholi', 'qwer', 'virat');


INSERT INTO `contact` (`addline1`, `addline2`, `city`, `ctype`, `email`, `personid`, `state`, `zip`) VALUES ('2381 Bower ave', 'Beverly hills', 'Los angeles', 'Primary', 'sheen@gmail.com', '1', 'California', '21312');
INSERT INTO `contact` (`addline1`, `addline2`, `city`, `ctype`, `email`, `personid`, `state`, `zip`) VALUES ('1231 Victory blvd', 'Woodland hills', 'Los angeles', 'Primary', 'emmaW@gmail.com', '2', 'California', '91367');
INSERT INTO `contact` (`addline1`, `addline2`, `city`, `ctype`, `email`, `personid`, `state`, `zip`) VALUES ('4750 Wilshire blvd', 'Ventura', 'Los angeles', 'Primary', 'bobcat@gmail.com', '3', 'California', '90010');

INSERT INTO `userroles` VALUES (1,'User','Active',1),(2,'User','Active',2),(3,'Admin','Active',1),(4,'ContentManager','Active',2),(5,'SuperAdmin','Active',1);
