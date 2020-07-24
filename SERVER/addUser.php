<?php 

require_once 'connect.php';

if ( $_SERVER['REQUEST_METHOD'] == 'POST' ){

    $nama = $_POST['nama'];
    $email = $_POST['email'];
	$NoTelpon = $_POST['NoTelpon'];
	$password = $_POST['password'];
	

    if ( $nama == '' || $email == '' ){

            $response = array("message"=>"Data anda kosong");
 
            echo json_encode($response);
    } else {

        $query = "INSERT INTO users (nama,email,password,NoTelpon) VALUES ('$nama', '$email','$password','$NoTelpon')";
	

        if ( mysqli_query($conn, $query) ){
            $response = array("message"=>"Data Berhasil ditambahkan");
 
            echo json_encode($response);
        } else {
            $response = array("message"=>"Data gagal ditambahkan");
 
            echo json_encode($response);
        }
    }

    mysqli_close($conn);

} else {
             $response = array("message"=>"Coba Lagi");
 
            echo json_encode($response);
}

?>