<?php

	require_once 'connect.php';

    if ($_SERVER['REQUEST_METHOD'] == 'GET' ) {
        $query = "SELECT * FROM products";
        $result = mysqli_query($conn, $query);
        $response = array();
        while( $row = mysqli_fetch_assoc($result) ){
            array_push($response, 
            array(
                'produk_id'=>$row['produk_id'], 
                'nama_produk'=>$row['nama_produk'], 
                'deskripsi_produk'=>$row['deskripsi_produk'],
				'kategori_produk_id'=>$row['kategori_produk_id'], 
                'id_satuan'=>$row['id_satuan'], 
                'harga'=>$row['harga'],
				'kategori_id'=>$row['kategori_id'],
				'id_supplier'=>$row['id_supplier'],
                'harga'=>$row['harga'],
				
				
				
				) 
            );
        }
        echo json_encode($response);   
    }
	else {
             $response = array("message"=>"Coba Lagi menggunakan get");
 
            echo json_encode($response);
}
	
?>