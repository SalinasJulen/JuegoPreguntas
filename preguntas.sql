-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 24-04-2024 a las 11:28:25
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `preguntas`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `preguntas_respuestas`
--

CREATE TABLE `preguntas_respuestas` (
  `id` int(11) NOT NULL,
  `enunciadoPregunta` varchar(255) NOT NULL,
  `respuesta1` varchar(255) NOT NULL,
  `respuesta2` varchar(255) NOT NULL,
  `respuesta3` varchar(255) NOT NULL,
  `respuesta4` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `preguntas_respuestas`
--

INSERT INTO `preguntas_respuestas` (`id`, `enunciadoPregunta`, `respuesta1`, `respuesta2`, `respuesta3`, `respuesta4`) VALUES
(1, '¿Quién es el jugador con el dorsal 42 en el Bayern de Múnich? ', 'Jamal Musiala', 'Harry Kane', 'Aleksandar Pavlovic', 'Sacha Boey'),
(2, '¿Qué juego ganó el GOTY en el año 2022?', 'Elden Ring', 'Baldurs Gate III', 'Red Dead Redemption 2', 'Valorant'),
(3, '¿Dónde se encuentra la Isla de Navidad?', 'Asia', 'África', 'Oceanía', 'Europa'),
(4, '¿Cuál de estos es un periférico de entrada?', 'Teclado', 'Monitor', 'Proyector', 'Altavoces'),
(5, '¿Qué es Javadoc?', ' Una utilidad de Oracle para la generación de documentación de APIs en formato HTML a partir de código fuente Java', 'Es un sistema operativo', 'Es un entorno de desarrollo', 'Un repositorio WEB basado en GIT'),
(6, '¿Qué Jugador metió el único gol en la final de la champions del año 2022?', 'Vinicius Jr', 'Jordan Henderson', 'Mohamed Salah', 'David Alaba'),
(7, '¿Quién escribió La Celestina?', 'Fernando de Rojas', 'Autor anónimo', 'Adolfo Suárez', 'Amador Rivas'),
(8, '¿Cómo se dice sala de estar en Euskera?', 'Egongela', 'Jostailua', 'Triangelua', 'Galtzerdia'),
(9, '¿Cuántos Emiratos Árabes hay?', '7', '3', '4', '9'),
(10, '¿Qué jugador metió el único gol en la final del año 2020 de la champions?', 'Kingsley Coman', 'Kylian Mbappé', 'Neymar Jr', 'Serge Gnabry'),
(11, 'De estos elementos, ¿Qué tiene Java?', 'Una máquina virtual ', 'Un compilador', 'Librerías', 'Todas las respuestas son correctas'),
(12, '¿Cuántos kilómetros cuadrados tiene el territorio de Chad?', '1,284 millones km²', '910 millones km²', '870 millones km²', '2485 millones km²'),
(13, '¿Cuánto mide el hombre más alto de Bolivia?', '2.26 m', '2.30 m', '2.08 m', '1.98 m'),
(14, '¿Cómo se dice molino en Euskera?', 'Errota', 'Baratza', 'Xomorroa', 'Lapikoa'),
(15, '¿Cuál es la capital de Congo?', 'Kinsasa', 'Mascate', 'Zaragoza', 'Dili');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `idUsuario` int(11) NOT NULL,
  `nombreUsuario` varchar(200) NOT NULL,
  `CorreoElectronico` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`idUsuario`, `nombreUsuario`, `CorreoElectronico`) VALUES
(17, 'Julen', 'ju.salinas@aulanz.net'),
(18, 'Alberto', 'aaramburu@aulanz.net');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `preguntas_respuestas`
--
ALTER TABLE `preguntas_respuestas`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`idUsuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `preguntas_respuestas`
--
ALTER TABLE `preguntas_respuestas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `idUsuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
