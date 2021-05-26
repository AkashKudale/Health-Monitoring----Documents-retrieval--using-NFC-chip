<?php

include 'db.php';

 
	 $e1=$_POST['e1'];
	$e2=$_POST['e2'];
	 $e3=$_POST['e3'];
	  
	$e4=date("d-m-Y");
				$sql="insert into notice (  `Title`, `Message`, `MSGDate`, `Sender`) values ('$e1','$e2','$e4','$e3')";
				if ($conn->query($sql) == TRUE) 
				{
					echo "Notice Added successfully";
				} 
				else 
				{
   					 echo "Not Added";
				}	
?>