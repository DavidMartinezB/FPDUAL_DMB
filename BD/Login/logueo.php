<?php
include('db.php');

$usuario = $_POST['usuario'];
$contrasena = $_POST['contrasena'];
session_start();
$_SESSION['usuario'] = $usuario;
$logueo = "SELECT*FROM usuarios WHERE usuario = '$usuario' and contrasena = '$contrasena'";

$validacion = mysqli_query($conexion, $logueo);
$filas = mysqli_fetch_row($validacion);

if ($filas>0) {
    header('location:inicio.php');
} else {
    echo "ha ocurrido un error, intentelo de nuevo más tarde";
}
mysqli_free_result($validacion);
?>