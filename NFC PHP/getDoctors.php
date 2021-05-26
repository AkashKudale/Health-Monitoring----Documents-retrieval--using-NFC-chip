<?php

include 'dbDetails.php';

 
	

	  	
		
		$sql="select * from doctor ORDER BY ID DESC";
		
		
		$res = mysqli_query($conn,$sql);
		$result = array();
		while($row = mysqli_fetch_array($res)){
		array_push($result,
		array(
			'id'=>$row[0],
			'name'=>$row[1],
			'contact'=>$row[2]
		));
		}
		 
		echo json_encode(array("result"=>$result));
	  
	  

	 
	  


?>