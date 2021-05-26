<?php

include 'dbDetails.php';

 
	 $uname=$_POST['e1'];

	  	$sql="select * from prescription where user_id='$e1'";
		$res = mysqli_query($conn,$sql);
		$result = array();
		while($row = mysqli_fetch_array($res)){
		array_push($result,
		array(
			
			'pres'=>$row[3],
			
		));
		}
		 
		echo json_encode(array("result"=>$result));
	  
	  

	 
	  


?>