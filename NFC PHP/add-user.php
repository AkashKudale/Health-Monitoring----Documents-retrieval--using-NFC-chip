<?php

include 'dbDetails.php';

 
	$e1=$_POST['e1'];
	$e2=$_POST['e2'];
	//$e1="abcd";
	//$e2="1234";
	$cnt=0;
	$e3=0;
	  
	$sql="select * from register";
	$result=$conn->query($sql);

	if($result->num_rows>0)
	{
		while($row=$result->fetch_assoc())
		{
			if($e1==$row["Username"])
			{
				$cnt=1;
			}
		}
	}
	
	
	if($cnt==0)
	{
				$rd=date('d/m/Y');
	 			$sql="insert into register (`Username`, `Password`) values ('$e1','$e2')";
				if ($conn->query($sql) == TRUE) 
				{
				
					echo "User Added successfully";
				} 
				else 
				{
   					 echo "Not Added";
				}	
	}
	else
	{
		echo "already exists";
		 
	}
		 
	  


?>