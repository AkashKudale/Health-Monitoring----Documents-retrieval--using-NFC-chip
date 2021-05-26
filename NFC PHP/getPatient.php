<?php

include 'dbDetails.php';

 
	 $uname=$_POST['e1'];
         $name="";
	 $f=0;
	 
	 //$uname="5678";
	 
	 
	 $sql="select * from users";
	$result=$conn->query($sql);

	if($result->num_rows>0)
	{
		while($row=$result->fetch_assoc())
		{
			if($uname==$row["id"])
			{
				//echo $row["fingerprint"];
                                $name=$row["name"];
				$f=1;
                                
			}
		}
	}
	
	if($f==0){
		echo "Not Found";
	
	}
        else
        {
                echo $name;
        }
	 
	 
	 

	  	/*$sql="select * from users where id='$e1'";
		$res = mysqli_query($conn,$sql);
		$result = array();
		while($row = mysqli_fetch_array($res)){
		array_push($result,
		array(
			
			echo ('$row[6]');
			
		));
		}*/
		 
		//echo json_encode(array("result"=>$result));
	  
	  

	 
	  


?>