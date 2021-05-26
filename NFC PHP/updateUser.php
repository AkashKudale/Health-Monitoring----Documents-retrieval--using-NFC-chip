<?php

include 'dbDetails.php';

 
	$e1=$_POST['e1'];
	$e2=$_POST['e2'];
	$e3=$_POST['e3'];
	$e4=$_POST['e4'];
	$e5=$_POST['e5'];
	
	
	
	
	/*
	$e1="5678";
	$e2="12345";
	$e3="2345t";
	$e4="345";
	$e5="jkl";*/

	 
	$sql="select * from users where id='$e1'";
	$result=$conn->query($sql);

	if($result->num_rows>0)
	{
		//update user
		
		$sql="update users set name='$e2', mobile='$e3', address='$e4',email='$e5' where id='$e1'";
		$conn->query($sql);
		echo "User updated";
				
		
	}
        
        else
        {
                
                $sql="select * from police where id='$e1'";
                $result=$conn->query($sql);
        
                if($result->num_rows>0)
                {
                        //update user
                        
                        $sql="update police set name='$e2', mobile='$e3', address='$e4',email='$e5' where id='$e1'";
                        $conn->query($sql);
                        echo "Police updated";
                                        
                        
                }
                
                else
                {
                      $sql="select * from rto where id='$e1'";
                        $result=$conn->query($sql);
                
                        if($result->num_rows>0)
                        {
                                //update user
                                
                                $sql="update rto set name='$e2', mobile='$e3', address='$e4',email='$e5' where id='$e1'";
                                $conn->query($sql);
                                echo "RTO updated";
                                                
                                
                        }
                        
                        else
                        {
                                $sql="select * from doctor where id='$e1'";
                                $result=$conn->query($sql);
                        
                                if($result->num_rows>0)
                                {
                                        //update user
                                        
                                        $sql="update doctor set name='$e2', mobile='$e3', address='$e4',email='$e5' where id='$e1'";
                                        $conn->query($sql);
                                        echo "RTO updated";
                                                        
                                        
                                }
                                
                                else
                                {
                                        
                                }     
                        }    
                } 
        }
        
	
	 
?>