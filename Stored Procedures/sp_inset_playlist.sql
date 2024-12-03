CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insert_playlist`(name_p varchar(45), songs_p int(11),duration_t int(11) )
BEGIN

   insert into playlist values (name_p,songs_p,duration_t);
END