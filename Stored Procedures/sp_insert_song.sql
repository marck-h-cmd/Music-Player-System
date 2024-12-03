CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insert_song`( name_s varchar(45), artist_s varchar(45),path_s varchar(255),genre_s varchar(45), duration int(11),playlist_s varchar(45))
BEGIN

 insert into  song (songname, artist, filepath, genre, duration, playlistname) values(name_s,artist_s,path_s, genre_s,duration,playlist_s);

END