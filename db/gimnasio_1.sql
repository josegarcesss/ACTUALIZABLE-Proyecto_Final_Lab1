-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 13-06-2024 a las 03:11:57
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
-- Base de datos: `gimnasio_1`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asistencia`
--

CREATE TABLE `asistencia` (
  `idAsistencia` int(11) NOT NULL,
  `idSocio` int(11) NOT NULL,
  `idClase` int(11) NOT NULL,
  `fechaAsistencia` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `asistencia`
--

INSERT INTO `asistencia` (`idAsistencia`, `idSocio`, `idClase`, `fechaAsistencia`) VALUES
(2, 1, 1, '2024-06-06'),
(4, 1, 1, '2024-06-12'),
(8, 2, 3, '2024-06-12');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clase`
--

CREATE TABLE `clase` (
  `idClase` int(11) NOT NULL,
  `nombre` varchar(40) NOT NULL,
  `idEntrenador` int(11) NOT NULL,
  `horario` time NOT NULL,
  `capacidad` int(30) NOT NULL,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `clase`
--

INSERT INTO `clase` (`idClase`, `nombre`, `idEntrenador`, `horario`, `capacidad`, `estado`) VALUES
(1, 'Boxeo', 1, '09:00:00', 3, 1),
(2, 'Peso Muerto', 2, '10:00:00', 5, 1),
(3, 'Boxing Junior', 1, '06:00:00', 9, 1),
(4, 'Zumba', 2, '14:00:00', 11, 1),
(5, 'Sparring', 1, '20:00:00', 6, 1),
(6, 'Taekwondo', 2, '11:00:00', 11, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `entrenador`
--

CREATE TABLE `entrenador` (
  `idEntrenador` int(11) NOT NULL,
  `dni` int(11) NOT NULL,
  `nombre` varchar(40) NOT NULL,
  `apellido` varchar(40) NOT NULL,
  `especialidad` varchar(40) NOT NULL,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `entrenador`
--

INSERT INTO `entrenador` (`idEntrenador`, `dni`, `nombre`, `apellido`, `especialidad`, `estado`) VALUES
(1, 18544684, 'Jorge', 'Gutierrez', 'Boxeo', 1),
(2, 43695857, 'Victor', 'Herrera', 'Kick Boxing', 1),
(3, 44385837, 'Marianella', 'Gutierrez', 'Yoga', 1),
(4, 43568122, 'Daniel', 'Perez', 'Tae Bo', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inscripcion`
--

CREATE TABLE `inscripcion` (
  `idInscripcion` int(11) NOT NULL,
  `idSocio` int(11) NOT NULL,
  `idClase` int(11) NOT NULL,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `inscripcion`
--

INSERT INTO `inscripcion` (`idInscripcion`, `idSocio`, `idClase`, `estado`) VALUES
(3, 2, 3, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `membresia`
--

CREATE TABLE `membresia` (
  `idMembresia` int(11) NOT NULL,
  `idSocio` int(11) NOT NULL,
  `cantidadPases` int(40) NOT NULL,
  `fechaInicio` date NOT NULL,
  `fechaFin` date NOT NULL,
  `costo` decimal(10,0) NOT NULL,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `membresia`
--

INSERT INTO `membresia` (`idMembresia`, `idSocio`, `cantidadPases`, `fechaInicio`, `fechaFin`, `costo`, `estado`) VALUES
(1, 1, 8, '2024-06-06', '2024-07-06', 1000, 1),
(2, 2, 16, '2024-05-05', '2024-06-05', 1500, 0),
(3, 1, 13, '2024-06-03', '2024-07-03', 1500, 1),
(5, 12, 16, '2024-06-12', '2024-07-12', 2000, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `socio`
--

CREATE TABLE `socio` (
  `idSocio` int(11) NOT NULL,
  `dni` int(11) NOT NULL,
  `nombre` varchar(40) NOT NULL,
  `apellido` varchar(40) NOT NULL,
  `edad` int(11) NOT NULL,
  `correo` varchar(40) NOT NULL,
  `telefono` int(11) NOT NULL,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `socio`
--

INSERT INTO `socio` (`idSocio`, `dni`, `nombre`, `apellido`, `edad`, `correo`, `telefono`, `estado`) VALUES
(1, 44596877, 'Gonzalo', 'Morales', 17, 'gomez@gmil.com', 260499001, 1),
(2, 45698325, 'Roman', 'Riquelme', 68582, 'mklohiso@djhkh.com', 45698, 1),
(3, 20994454, 'Carlos', 'Montaner', 23, 'vivanlasvegas@gmil.co', 266548699, 0),
(4, 20994457, 'Carlos', 'de la Torre', 23, 'CarlitosPerez@hotmail.com', 266587984, 1),
(9, 456987, 'Jose', 'Garces', 22, 'sjkodafoi@gmail.com', 1234657, 0),
(12, 45698321, 'hola', 'sad', 17, 'asdas@gmlo.com', 26641, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `asistencia`
--
ALTER TABLE `asistencia`
  ADD PRIMARY KEY (`idAsistencia`),
  ADD KEY `idClase` (`idClase`),
  ADD KEY `idSocio` (`idSocio`);

--
-- Indices de la tabla `clase`
--
ALTER TABLE `clase`
  ADD PRIMARY KEY (`idClase`),
  ADD KEY `idEntrenador` (`idEntrenador`);

--
-- Indices de la tabla `entrenador`
--
ALTER TABLE `entrenador`
  ADD PRIMARY KEY (`idEntrenador`),
  ADD UNIQUE KEY `dni` (`dni`);

--
-- Indices de la tabla `inscripcion`
--
ALTER TABLE `inscripcion`
  ADD PRIMARY KEY (`idInscripcion`),
  ADD KEY `inscripcion_ibfk_1` (`idClase`),
  ADD KEY `inscripcion_ibfk_2` (`idSocio`);

--
-- Indices de la tabla `membresia`
--
ALTER TABLE `membresia`
  ADD PRIMARY KEY (`idMembresia`),
  ADD KEY `idSocio` (`idSocio`);

--
-- Indices de la tabla `socio`
--
ALTER TABLE `socio`
  ADD PRIMARY KEY (`idSocio`),
  ADD UNIQUE KEY `dni` (`dni`),
  ADD UNIQUE KEY `correo` (`correo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `asistencia`
--
ALTER TABLE `asistencia`
  MODIFY `idAsistencia` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `clase`
--
ALTER TABLE `clase`
  MODIFY `idClase` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `entrenador`
--
ALTER TABLE `entrenador`
  MODIFY `idEntrenador` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `inscripcion`
--
ALTER TABLE `inscripcion`
  MODIFY `idInscripcion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `membresia`
--
ALTER TABLE `membresia`
  MODIFY `idMembresia` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `socio`
--
ALTER TABLE `socio`
  MODIFY `idSocio` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `asistencia`
--
ALTER TABLE `asistencia`
  ADD CONSTRAINT `asistencia_ibfk_1` FOREIGN KEY (`idClase`) REFERENCES `clase` (`idClase`),
  ADD CONSTRAINT `asistencia_ibfk_2` FOREIGN KEY (`idSocio`) REFERENCES `socio` (`idSocio`);

--
-- Filtros para la tabla `clase`
--
ALTER TABLE `clase`
  ADD CONSTRAINT `clase_ibfk_1` FOREIGN KEY (`idEntrenador`) REFERENCES `entrenador` (`idEntrenador`);

--
-- Filtros para la tabla `membresia`
--
ALTER TABLE `membresia`
  ADD CONSTRAINT `membresia_ibfk_1` FOREIGN KEY (`idSocio`) REFERENCES `socio` (`idSocio`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
