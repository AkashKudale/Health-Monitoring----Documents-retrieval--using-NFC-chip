<?php

include 'dbDetails.php';

 
	$e1=$_POST['e1'];
	$e2=$_POST['e2'];
	$e3=$_POST['e3'];
	
	
	
	  
	
	
	
	
				
	 			$sql="insert into prescription (`id`, `user_id`,`doc_id`,`pres`) values (NULL,'$e2','$e1','$e3')";
				if ($conn->query($sql) == TRUE) 
				{
				
					echo "User Added successfully";
				} 
				else 
				{
   					 echo "Not Added";
				}	
	
	  


?>