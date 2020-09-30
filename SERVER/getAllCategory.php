<?php

	require_once 'connect.php';

    if ($_SERVER['REQUEST_METHOD'] == 'GET' ) {
        $query = "SELECT * FROM category";
        $result = mysqli_query($conn, $query);
        $response = array();
        while( $row = mysqli_fetch_assoc($result) ){
            array_push($response, 
            array(
                'category_id'=>$row['category_id'], 
                'category_name'=>$row['category_name']) 
            );
        }
        echo json_encode($response);   
    }
	else {
             $response = array("message"=>"Coba Lagi menggunakan get");
 
            echo json_encode($response);
}
	
?>