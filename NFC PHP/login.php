<?php

include 'dbDetails.php';

 
	$e1=$_POST['e1'];
	$e2=$_POST['e2'];
	
       
	$sql="select * from register where Username='$e1' and Password='$e2'";
        $cnt=0;
        
    
	$result=$conn->query($sql);

	if($result->num_rows>0)
	{
		while($row=$result->fetch_assoc())
		{
			if($e1==$row["Username"] && $e2=$row["Password"])
			{
				$cnt=1;
			}
			 
		}
	
        }
        else
        {
        }
        
        if($cnt==1)
	{
		echo "successful";
	}
	else
	{
		echo "wrong";
	}
        
	/*$result=$conn->query($sql);

	if($result->num_rows>0)
	{
		echo "successful";
	}
	else
	{
		echo "wrong";
	}*/
?>