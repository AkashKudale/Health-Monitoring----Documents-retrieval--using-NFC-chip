<?php

include 'dbDetails.php';

 
	$e1=$_POST['e1'];
	$e2=$_POST['e2'];
	$e3=$_POST['e3'];
	$e4=$_POST['e4'];
	$e5=$_POST['e5'];
	$e6=$_POST['e6'];
	
	/*
	$e1="5678";
	$e2="abc";
	$e3="def";
	$e4="ghi";
	$e5="jkl";
	$e6="mno";
	$e7="pqr";*/
	 
	$sql="select * from rto where id='$e1'";
	$result=$conn->query($sql);

	if($result->num_rows>0)
	{
		echo "Already Exists";
	}
	else
	{
	
			$sql="insert into rto values('$e1','$e2','$e3','$e4','$e5','$e6')";
			if ($conn->query($sql) == TRUE) 
				{
				
					echo "User Added successfully";
				} 
				else 
				{
   					 echo "Not Added";
				}	
	
	
	
		
	}
?>