<?php

include 'dbDetails.php';

 
	$e1=$_POST['e1'];
	
	 
	$sql="select * from users where id='$e1' ";
	$result=$conn->query($sql);

	if($result->num_rows>0)
	{
		echo "successful";
	}
	else
	{
		echo "wrong";
	}
?>