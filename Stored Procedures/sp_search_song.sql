CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_buscar_musica`(
    IN p_songname varchar(45)
)
BEGIN
    SELECT * FROM song WHERE songname = p_songname;
END