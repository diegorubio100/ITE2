create sequence Alohandes_sequence;


CREATE TABLE A_VINCULADOUNIANDES (
    id NUMBER PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    CONSTRAINT CK_VinculadoUniandes_tipo CHECK (tipo IN ('Estudiante', 'Egresado', 'Empleado', 'Acudiente', 'Invitado'))
);


CREATE TABLE A_HABITACION (
    id NUMBER,
    tipo VARCHAR(50) NOT NULL,
    CONSTRAINT PK_Habitacion PRIMARY KEY (id),
    CONSTRAINT CK_Habitacion_tipo CHECK (tipo IN ('Hotel', 'Hostal', 'Fenicia', 'AlquilaMes', 'AlquilaDia', 'ResidenciaUniversitaria'))
);

CREATE TABLE A_RESERVA (
    id NUMBER,
    idCliente NUMBER NOT NULL,
    idHabitacion NUMBER NOT NULL,
    fechaReserva DATE NOT NULL,
    fechaCancelacionOportuna DATE NOT NULL,
    fechaCancelacion DATE,
    CONSTRAINT PK_Reserva PRIMARY KEY (id),
    CONSTRAINT FK__Reserva_idCliente FOREIGN KEY (idCliente) REFERENCES A_VinculadoUniandes (id),
    CONSTRAINT FK_Reserva_idHabitacion FOREIGN KEY (idHabitacion) REFERENCES A_Habitacion (id)
);

CREATE TABLE A_DISPONIBILIDAD (
    fecha DATE,
    idHabitacion NUMBER,
    idReserva NUMBER,
    disponible CHAR(1) DEFAULT 'N' NOT NULL,
    CONSTRAINT PK_Disponibilidad PRIMARY KEY (fecha, idHabitacion),
    CONSTRAINT FK_Disponibilidad_idHabitacion FOREIGN KEY (idHabitacion) REFERENCES A_Habitacion (id),
    CONSTRAINT FK_Disponibilidad_idReserva FOREIGN KEY (idReserva) REFERENCES A_Reserva (id),
    CONSTRAINT CK_Disponibilidad_disponible CHECK ((disponible) IN ('N', 'Y'))
);


CREATE TABLE A_HOTEL (
    id NUMBER PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE,
    restaurante CHAR(1) DEFAULT 'N' NOT NULL,
    piscina CHAR(1) DEFAULT 'N' NOT NULL,
    parqueadero CHAR(1) DEFAULT 'N' NOT NULL,
    internet CHAR(1) DEFAULT 'N' NOT NULL,
    tv CHAR(1) DEFAULT 'N' NOT NULL,
    recepcion CHAR(1) DEFAULT 'N' NOT NULL,
    CONSTRAINT CK_Hotel_restaurante CHECK (restaurante IN ('N', 'Y')),
    CONSTRAINT CK_Hotel_piscina CHECK (piscina IN ('N', 'Y')),
    CONSTRAINT CK_Hotel_parqueadero CHECK (parqueadero IN ('N', 'Y')),
    -- CONSTRAINT CK_Hotel_wifi CHECK (wifi IN ('N', 'Y')),
    CONSTRAINT CK_Hotel_tv CHECK (tv IN ('N', 'Y')),
    CONSTRAINT CK_Hotel_recepcion CHECK (recepcion IN ('N', 'Y'))
);


CREATE TABLE A_HOSTAL (
    id NUMBER PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE,
    horaApertura INTERVAL DAY(0) TO SECOND (0) NOT NULL,
    horaCierre INTERVAL DAY(0) TO SECOND (0) NOT NULL
);

CREATE TABLE A_FENICIA (
    id NUMBER PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE
);


CREATE TABLE A_ALQUILAMES (
    id NUMBER PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE,
    idMiembro NUMBER NOT NULL,
    CONSTRAINT FK_AlquilaMes_idMiembro FOREIGN KEY (idMiembro) REFERENCES A_VinculadoUniandes (id)
);


CREATE TABLE A_ALQUILADIA (
    id NUMBER PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE,
    idMiembro NUMBER NOT NULL,
    CONSTRAINT FK_AlquilaDia_idMiembro FOREIGN KEY (idMiembro) REFERENCES A_VinculadoUniandes (id)
);


CREATE TABLE A_RESIDENCIAUNIVERSITARIA (
    id NUMBER PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE,
    restaurante CHAR(1) DEFAULT 'N' NOT NULL,
    salaEstudio CHAR(1) DEFAULT 'N' NOT NULL,
    salaEsparcimiento CHAR(1) DEFAULT 'N' NOT NULL,
    gimnasio CHAR(1) DEFAULT 'N' NOT NULL,
    CONSTRAINT CK_ResidenciaUniversitaria_restaurante CHECK (restaurante IN ('N', 'Y')),
    CONSTRAINT CK_ResidenciaUniversitaria_salaEstudio CHECK (salaEstudio IN ('N', 'Y')),
    CONSTRAINT CK_ResidenciaUniversitaria_salaEsparcimiento CHECK (salaEsparcimiento IN ('N', 'Y')),
    CONSTRAINT CK_ResidenciaUniversitaria_gimnasio CHECK (gimnasio IN ('N', 'Y'))
);


CREATE TABLE A_HABITACIONHOTEL(
    idHabitacion NUMBER PRIMARY KEY,
    idHotel NUMBER NOT NULL,
    tipoHabitacion VARCHAR2(15) NOT NULL CHECK (tipoHabitacion IN ('Estandar', 'Semisuite', 'Suite')),
    capacidad NUMBER NOT NULL CHECK (capacidad > 0),
    ubicacion VARCHAR2(50) NOT NULL,
    tamanio NUMBER NOT NULL CHECK (tamanio > 0),
    baniera CHAR(1) NOT NULL CHECK (baniera IN ('Y', 'N')),
    yacuzzi CHAR(1) NOT NULL CHECK (yacuzzi IN ('Y', 'N')),
    sala CHAR(1) NOT NULL CHECK (sala IN ('Y', 'N')),
    cocineta CHAR(1) NOT NULL CHECK (cocineta IN ('Y', 'N')),
    precioNoche NUMBER NOT NULL CHECK (precioNoche > 0),
    CONSTRAINT FK_HabitacionHotel_idHabitacion FOREIGN KEY (idHabitacion) REFERENCES A_Habitacion (id),
    CONSTRAINT FK_HabitacionHotel_idHotel FOREIGN KEY (idHotel) REFERENCES A_Hotel (id)
);



CREATE TABLE A_HABITACIONHOSTAL (
    idHabitacion NUMBER PRIMARY KEY,
    idHostal NUMBER NOT NULL,
    capacidad NUMBER NOT NULL CHECK (capacidad > 0),
    tamanio NUMBER NOT NULL CHECK (tamanio > 0),
    precioNoche NUMBER NOT NULL CHECK (precioNoche > 0),
    CONSTRAINT FK_HabitacionHostal_idHabitacion FOREIGN KEY (idHabitacion) REFERENCES A_Habitacion (id),
    CONSTRAINT FK_HabitacionHostal_idHostal FOREIGN KEY (idHostal) REFERENCES A_Hotel (id)
);


CREATE TABLE A_HABITACIONFENICIA (
    idHabitacion NUMBER PRIMARY KEY,
    idPersonaFenicia NUMBER NOT NULL,
    precio NUMBER NOT NULL CHECK (precio > 0),
    precioPrePagar NUMBER NOT NULL CHECK (precioPrePagar > 0),
    minimoMeses NUMBER NOT NULL CHECK (minimoMeses >= 1),
    comidas CHAR(1) NOT NULL CHECK (comidas IN ('Y', 'N')),
    cocina CHAR(1) NOT NULL CHECK (cocina IN ('Y', 'N')),
    tipoBanio VARCHAR2(15) NOT NULL CHECK (tipoBanio IN ('Privado', 'Compartido')),
    tipoHabitacion VARCHAR2(15) NOT NULL CHECK (tipoHabitacion IN ('Individual', 'Compartida')),
    luz NUMBER NOT NULL CHECK (luz >= 0),
    telefono NUMBER NOT NULL CHECK (telefono >= 0),
    agua NUMBER NOT NULL CHECK (agua >= 0),
    tv NUMBER NOT NULL CHECK (tv >= 0),
    internet NUMBER NOT NULL CHECK (internet >= 0),
    CONSTRAINT FK_HabitacionFenicia_idHab FOREIGN KEY (idHabitacion) REFERENCES A_Habitacion (id),
    CONSTRAINT FK_HabitacionFenicia_idPersonaFenicia FOREIGN KEY (idPersonaFenicia) REFERENCES A_Fenicia (id)
);


CREATE TABLE A_APARTAMENTOCOMUNIDAD (
    idHabitacion NUMBER PRIMARY KEY,
    idMiembroAlquila NUMBER NOT NULL,
    precio NUMBER NOT NULL CHECK (precio > 0),
    amoblado CHAR(1) NOT NULL CHECK (amoblado IN ('Y', 'N')),
    minimoMeses NUMBER NOT NULL CHECK (minimoMeses >= 1),
    serviciosPublicos CHAR(1) NOT NULL CHECK (serviciosPublicos IN ('Y', 'N')),
    tv CHAR(1) NOT NULL CHECK (tv IN ('Y', 'N')),
    internet CHAR(1) NOT NULL CHECK (internet IN ('Y', 'N')),
    administracion CHAR(1) NOT NULL CHECK (administracion IN ('Y', 'N')),
    CONSTRAINT FK_ApartamentoComunidad_idHabitacion FOREIGN KEY (idHabitacion) REFERENCES A_Habitacion (id),
    CONSTRAINT FK_ApartamentoComunidad_idMiembroAlquila FOREIGN KEY (idMiembroAlquila) REFERENCES A_AlquilaMes (id)
);


CREATE TABLE A_VIVIENDA (
    idHabitacion NUMBER PRIMARY KEY,
    idAlquilerDias NUMBER NOT NULL,
    precio NUMBER NOT NULL CHECK (precio > 0),
    habitaciones NUMBER NOT NULL CHECK (habitaciones > 0),
    ubicacion VARCHAR(100) NOT NULL ,
    menaje CHAR(1) NOT NULL CHECK (menaje IN ('Y', 'N')),
    caracSeguro NUMBER NOT NULL CHECK (caracSeguro > 0),
    CONSTRAINT FK_Vivienda_idhabitacion FOREIGN KEY (idHabitacion) REFERENCES A_Habitacion (id),
    CONSTRAINT FK_Vivienda_idAlquilerDias FOREIGN KEY (idAlquilerDias) REFERENCES A_AlquilaDia (id)
);


CREATE TABLE A_HABITACIONUNIVERSITARIA (
  idHabitacion NUMBER PRIMARY KEY,
  idResidenciaUniversitaria NUMBER NOT NULL,
  precio NUMBER NOT NULL CHECK (precio > 0),
  capacidad NUMBER NOT NULL CHECK (capacidad > 0),
  ubicacion VARCHAR2(100) NOT NULL,
  opcion VARCHAR2(100) NOT NULL,
  amoblado CHAR(1) NOT NULL CHECK (amoblado IN ('Y', 'N')),
  cocineta CHAR(1) NOT NULL CHECK (cocineta IN ('Y', 'N')),
  internet CHAR(1) NOT NULL CHECK (internet IN ('Y', 'N')),
  tv CHAR(1) NOT NULL CHECK (tv IN ('Y', 'N')),
  serviciosPublicos CHAR(1) NOT NULL CHECK (serviciosPublicos IN ('Y', 'N')),
  porteriaCompleta CHAR(1) NOT NULL CHECK (porteriaCompleta IN ('Y', 'N')),
  aseo CHAR(1) NOT NULL CHECK (aseo IN ('Y', 'N')),
  apoyoSocial CHAR(1) NOT NULL CHECK (apoyoSocial IN ('Y', 'N')),
  apoyoAcademico CHAR(1) NOT NULL CHECK (apoyoAcademico IN ('Y', 'N')),
  tipoHabitacion VARCHAR2(15) NOT NULL CHECK (tipoHabitacion IN ('Individual', 'Compartida')),
  menaje CHAR(1) NOT NULL CHECK (menaje IN ('Y', 'N')),
  CONSTRAINT FK_HabitacionUniversitaria_idHabitacion FOREIGN KEY (idHabitacion) REFERENCES A_Habitacion (id),
  CONSTRAINT FK_HabitacionUniversitaria_idResidenciaUniversitaria FOREIGN KEY (idResidenciaUniversitaria) REFERENCES A_ResidenciaUniversitaria (id)
);
commit;