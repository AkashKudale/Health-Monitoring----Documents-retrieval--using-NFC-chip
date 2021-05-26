<?php

include 'dbDetails.php';

 
	 $uname=$_POST['e1'];
	//$uname="54333";


        $sql="select * from users where id='$uname'";
	$resultc=$conn->query($sql);

	if($resultc->num_rows>0)
	{
	  	$sql="select * from users where id='$uname'";
		$res = mysqli_query($conn,$sql);
		
		
		$result = array();
		while($row = mysqli_fetch_array($res)){
		array_push($result,
		array(
			'name'=>$row[1],
			'mobile'=>$row[2],
			'address'=>$row[3],
			'email'=>$row[4],
			
		));
		}
		 
		echo json_encode(array("result"=>$result));
	  
	  }else{
          
          
                   $sql="select * from police where id='$uname'";
                        $resultc=$conn->query($sql);
                
                        if($resultc->num_rows>0)
                        {
                                $sql="select * from police where id='$uname'";
                                $res = mysqli_query($conn,$sql);
                                
                                
                                $result = array();
                                while($row = mysqli_fetch_array($res)){
                                array_push($result,
                                array(
                                        'name'=>$row[1],
                                        'mobile'=>$row[2],
                                        'address'=>$row[3],
                                        'email'=>$row[4],
                                        
                                ));
                                }
                                 
                                echo json_encode(array("result"=>$result));
                          
                          }else{
                          
                          
                                  $sql="select * from rto where id='$uname'";
                                $resultc=$conn->query($sql);
                        
                                if($resultc->num_rows>0)
                                {
                                        $sql="select * from rto where id='$uname'";
                                        $res = mysqli_query($conn,$sql);
                                        
                                        
                                        $result = array();
                                        while($row = mysqli_fetch_array($res)){
                                        array_push($result,
                                        array(
                                                'name'=>$row[1],
                                                'mobile'=>$row[2],
                                                'address'=>$row[3],
                                                'email'=>$row[4],
                                                
                                        ));
                                        }
                                         
                                        echo json_encode(array("result"=>$result));
                                  
                                  }else{
                                  
                                  
                                                $sql="select * from doctor where id='$uname'";
                                                $resultc=$conn->query($sql);
                                        
                                                if($resultc->num_rows>0)
                                                {
                                                        $sql="select * from doctor where id='$uname'";
                                                        $res = mysqli_query($conn,$sql);
                                                        
                                                        
                                                        $result = array();
                                                        while($row = mysqli_fetch_array($res)){
                                                        array_push($result,
                                                        array(
                                                                'name'=>$row[1],
                                                                'mobile'=>$row[2],
                                                                'address'=>$row[3],
                                                                'email'=>$row[4],
                                                                
                                                        ));
                                                        }
                                                         
                                                        echo json_encode(array("result"=>$result));
                                                  
                                                  }else{
                                                  
                                                  
                                                          echo "Not User Available";
                                                  
                                                  }
                                  
                                  }
                          
                          }
                          
	  
	
	  
	  
	}
	 
	  


?>