<?php

include 'dbDetails.php';

 
	$e1=$_POST['e1'];
	$e2=$_POST['e2'];
	
	/*$e1="abc";
	$e2="dc";*/
	 
	$sql="select * from rto where id='$e1' and pass='$e2'";
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