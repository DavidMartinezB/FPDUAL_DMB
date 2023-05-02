<?php
include('db.php');

$nombre = $_POST['nombre'];
$usuario = $_POST['usuario'];
$correo = $_POST['correo'];
$contrasena = $_POST['contrasena'];

$peticion = "INSERT INTO usuarios (correo, nombre, usuario, contrasena) values ('$correo', '$nombre', '$usuario', '$contrasena')";

$validacion = mysqli_query($conexion, $peticion);

if ($validacion) {
    header('location:init.php');
} else {
    echo "me da a mi que no";
}
?>