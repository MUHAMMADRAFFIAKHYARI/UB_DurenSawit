<?php 

  require_once 'connect.php';
  
  $response = array();
  
  if (isset($_POST['email'])) {
  	$email = $_POST['email'];
  	$sql_e = "SELECT * FROM users WHERE email='$email'";
  	$res_e = mysqli_query($conn, $sql_e);

  	if(mysqli_num_rows($res_e) > 0){
        $response = array("value" => "0");
  	} else{
        $response = array("value" => "1");
	}
	
	echo json_encode($response);
	mysqli_close($conn);
	
  } else{
    $response = array("value" => "0",
                     "message" => "email kosong");
	echo json_encode($response);
	mysqli_close($conn);
	  
  }
?>