CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_list_song`()
BEGIN
   select * from song;
END