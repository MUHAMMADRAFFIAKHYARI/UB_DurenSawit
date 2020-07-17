<?php 

require_once 'connection.php';

if ( $_SERVER['REQUEST_METHOD'] == 'POST' ){

    $name = $_POST['name'];
    $email = $_POST['email'];
	$NoTelpon = $_POST['NoTelpon'];
	$password = $_POST['password'];
	

    if ( $name == '' || $email == '' ){

            $response["value"] = 0;
            $response["message"] = "data anda kosong";
            echo json_encode($response);

    } else {

        $query = "INSERT INTO users (name,email,password,NoTelpon) VALUES ('$name', '$email','$password','$NoTelpon')";
	

        if ( mysqli_query($conn, $query) ){
            $response["value"] = 1;
            $response["message"] = $name." Sukses ditambahkan";
            echo json_encode($response);
        } else {
            $response["value"] = 0;
            $response["message"] = "Oops! ".$name." Gagal ditambahkan, \n Silahkan Coba lagi!";
            echo json_encode($response);
        }
    }

    mysqli_close($conn);

} else {
    $response["value"] = 0;
    $response["message"] = "oops! Coba lagi!";
    echo json_encode($response);
}

?>