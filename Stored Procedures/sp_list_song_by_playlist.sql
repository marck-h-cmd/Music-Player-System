CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_list_song_by_playlist`(name_p varchar(45))
BEGIN
    select * from song where playlistname=name_p;
END