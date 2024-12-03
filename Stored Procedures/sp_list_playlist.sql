CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_list_playlist`()
BEGIN
  select * from playlist;
END