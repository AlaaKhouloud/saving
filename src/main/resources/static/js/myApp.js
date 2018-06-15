 
  
 var myApp=angular.module("myApp",['ngRoute','ngCookies']);

 myApp.config(function($routeProvider) {
	    $routeProvider.
	     when('/search', {
	    	  templateUrl:'/views/terrain.html',
		      controller:'TerrainController'  
	      }).
	      when('/reserve', {
	    	  templateUrl:'/views/reservation.html',
		      controller:'ReservationController'  
	      }).
	      when('/details', {
	    	  templateUrl:'/views/details.html',
		      controller:'DetailsController'  
	      }).
	      otherwise({ 
	    	  redirectTo: 'index.html',
	    	  controller:'FirstController'
	      });
	});
/*Gérer les route
app.config(function($stateProvider, $urlRouterProvider) {

    $stateProvider.state('home', {
				            url: '/home',
				            templateUrl: '/index.html',
				            controller:'IndexController'
				        })
				    .state('lister', {
				    	url: '/lister',
				    	templateUrl:'listerEtudiants.html',
						controller:'ListerController'     
				        })
				    .state('inscription', {
				        	url: '/inscription',
				        	templateUrl:'inscription.html',
				        	controller:'InscriptionController'   
				        });
});
myApp.controller("LogoutController" ,  function($scope,$http){
	 $scope.deconnexion=function($scope,$http){
		 $http.get('/logout');
	 }
	 $scope.deconnexion();
});
*/
 
 
 var x=null;
 var type = null;
 var date = null;
 var idclient =null;
//Page Terrains controlleur
myApp.controller("TerrainController",  function($scope,$http,$window,$cookies){
	x=null;
	$scope.disponibility=null;
	
	$scope.look=function(){
		if(type==null && date==null) $window.location.href = ('http://localhost:5454/');
		else{
		$http.get('/search?terrain='+type+'&date='+date)
		.then(function(response){	
			var data = response.data;
			$scope.disponibility=data;
			//$scope.mode=0;
		  }),
			function (error){
			   console.log(err);
		  };
		}
	}
	$scope.look();
	$scope.show=false;
	$scope.
	$scope.Reserver=function(p){
		if($cookies.get('cookieemail') != null){
		    // x=p;
			$http.get('/clientid?email='+$cookies.get('cookieemail'))
			.then(function(response){	
			    idclient = response.data;
			    console.log(idclient);
				//$window.location.href = ('http://localhost:5454/#!/reserve');
			  }),
				function (error){
				   console.log(err);
			  };
			
		    
		}else{
		   //ask him to login
		    document.getElementById("B1").setAttribute("data-target", "#myModal");
		}
	}
});

//Page details controlleur
myApp.controller("DetailsController" ,  function($scope,$http,$window){
	$scope.details=null;
	//details
	$scope.listerResevations=function(){
		if(x!=null){
			$http.get('/details?id='+x)
			.then(function(response){
				var data = response.data;
				$scope.details=data;
				
				//x=null;
			 }),
			 function (error){
				   console.log(err);
			 };
		}
		else{
			$window.location.href = ('http://localhost:5454/#!');
		}
	}
	
	$scope.listerResevations();
});

//Page reservation controlleur
myApp.controller("ReservationController" ,  function($scope,$http,$window){
	$scope.confirm=null;
	//reservation
	$scope.reserver=function(){
		if(x!=null){
			$http.post('/reserver?idT='+x+'&idc='+y+'&date='+d)
			.then(function(response){
				var data = response.data;
				$scope.confirm=data;
			 }),
			 function (error){
				   console.log(err);
			 };
		}
		else{
			$window.location.href = ('http://localhost:5454/#!');
		}
	}
});

var mode;
//Page Index controlleur
myApp.controller("FirstController" ,  function($scope,$http,$window,$cookies){
	$scope.types={};
	//$scope.mode=$cookies.put('cookiecon',2); 
	$scope.username=$cookies.get('cookiename');
	$scope.g_image=$cookies.get('cookieimg'); 
	$scope.mode=$cookies.get('cookiecon'); 
	//forms
	$scope.visiteur={};
	//
	/////form errors
	$scope.notfound = false;
	//////////////
	//lister les types
	$scope.lister=function(){
		$http.get('/terrainType')
		.then(function(response){
			var data = response.data;
			$scope.types=data;
		  }),
			function (error){
			   console.log(err);
		  };
	}

	//rechercher des créneaux
	$scope.Search=function(p){		
		//fill with data
		type = $scope.type;
		var day = p.getDate();  
		var month = p.getMonth()+1;  
		var year = p.getFullYear();
		date = year+"-"+month+"-"+day;
        $window.location.href = ('http://localhost:5454/#!/search');
	 }
	
	//////treatments on forms
	$scope.errorE = 0;
	$scope.errorM = 0;
	$scope.errorEm = 0;
	$scope.found = false;
	$scope.confirm=false;
	
	$scope.connexion=function(){
		$scope.mobileEmailCheck($scope.visiteur.telemail);
		if($scope.errorE == 0 && $scope.visiteur.telemail!='' && $scope.visiteur.pass!=''){
			$scope.reqinputs=false;
			$http.get('/checkE?email='+$scope.visiteur.telemail+'&password='+$scope.visiteur.pass)
			.then(function(response){ 
				 if(response.data==0){
					 $scope.notfound = true; 
				 }else{ 
					    $scope.g_image = 'img/user.png';
	     				$scope.username = response.data; 
						$cookies.put('cookiename',response.data);
	     				$cookies.put('cookieimg','img/user.png');
	     				$cookies.put('cookieemail',$scope.visiteur.telemail);
	     				$cookies.put('cookiecon',1);
	     				$scope.mode=$cookies.get('cookiecon');
				 }
			  }),
			  function (error){
				console.log(error);
			  };
		}else{
			$scope.reqinputs=true;
		}
	}
	$scope.inscription=function(){
		$scope.mobileCheck($scope.visiteur.telephone);
		$scope.EmailCheck($scope.visiteur.email);
		if($scope.errorEm ==0 && $scope.errorM == 0 && $scope.visiteur.nom_prenom!='' && $scope.visiteur.password!=''){
		$http.post('/save?nom='+$scope.visiteur.nom_prenom+'&email='+$scope.visiteur.email+'&tel='+$scope.visiteur.telephone+'&password='+$scope.visiteur.password+'&statut=manual')
			.then(function(response){ 
				 if(response.data==false){
					 $scope.found = true;
				 }else{
				    $scope.g_image = 'img/user.png';
     				$scope.username = $scope.visiteur.nom_prenom; 
					$cookies.put('cookiename',$scope.visiteur.nom_prenom);
     				$cookies.put('cookieimg','img/user.png');
     				$cookies.put('cookieemail',$scope.visiteur.email);
     				$cookies.put('cookiecon',1);
     				$scope.mode=$cookies.get('cookiecon');
				 }
			  }),
			  function (error){
				console.log(error);
			  };
		}
		else{
			$scope.message = "Veuillez fournir tous les champs";
		}
	}
	//regexTest
	$scope.mobileEmailCheck = function(p){
	   var mobileRegex = /^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/;
	   var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
	   if((mobileRegex.test(p) && p.length ===10)||(emailRegex.test(p))){
	     $scope.errorE = 0;
	  }else{
	    $scope.errorE = 1;
	  }
	}
	$scope.mobileCheck = function(p){
		   var mobileRegex = /^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/;
		   if(mobileRegex.test(p) && p.length ===10){
		     $scope.errorM = 0;
		  }else{
		    $scope.errorM = 1;
		  }
	}
	$scope.EmailCheck = function(p){
		var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
		if(emailRegex.test(p)){
		     $scope.errorEm = 0;
		  }else{
		    $scope.errorEm = 1;
		  }
	}
    
/////////////////////////////////////////////////////////////////////
	//first one to call
	$scope.lister();
	
	$scope.gmail={
			username:"",
			email:""
		};
		
		$scope.onGoogleLogin=function(){
			var request =null;
			var params={
					'clientid' : '13857849682-lbtm8302qp4t98vhl8k8npb2oaqqbjpb.apps.googleusercontent.com',
			        'cookiepolicy': 'single_host_origin',
			        'callback': function(result){
			        	 if(result['status']['signed_in']){
			        		 gapi.client.load('plus', 'v1', $scope.fetchGoogleProfile);	        		
			        	}
			         },
			         'approvalprompt': 'force',
			         'scope': 'https://www.googleapis.com/auth/plus.login https://www.googleapis.com/auth/plus.profile.emails.read'
			};
			
			gapi.auth.signIn(params);
		}
		
		$scope.fetchGoogleProfile=function() {
			  gapi.client.plus.people.get({userId: 'me'}).execute(
					  function(resp){
		        			$scope.$apply(function(){
		        				$scope.gmail.username = resp.displayName;
		        				$scope.gmail.email = resp.emails[0].value;
		        				$scope.g_image = resp.image.url;
		        				$scope.username = $scope.gmail.username; 
		        				//look for infos and register if not there
		        				//populate session
		        				$cookies.put('cookiename',$scope.gmail.username);
		        				$cookies.put('cookieimg',$scope.g_image);
		        				$cookies.put('cookieemail',$scope.gmail.email);
		        				$cookies.put('cookiecon',1);
		        				$scope.mode=$cookies.get('cookiecon');
		        			});
		        		}
			  );
		};
		$scope.processResponse=function(resp) {
			 resp.image.url 
		};
		
		//////////////////////////////////////////////////////////////////////////////////////
		$scope.facebook={
				username:"",
				email:""
			};
		$scope.onFBLogin=function(){
			FB.login(function(response){
				if(response.authResponse){
					FB.api('/me','GET',{fields: 'email,first_name,name,id,picture'},function(response){
						$scope.$apply(function(){
							$scope.facebook.username = response.name;
							$scope.facebook.email = response.email;
							$scope.fb_image = response.picture.data.url;
							$scope.username = $scope.facebook.username;
							$scope.g_image = $scope.fb_image;
							//populate session
							$cookies.put('cookiename',$scope.facebook.username);
	        				$cookies.put('cookieimg',$scope.fb_image);
	        				$cookies.put('cookieemail',$scope.facebook.email);
	        				$cookies.put('cookiecon',1);
	        				$scope.mode=$cookies.get('cookiecon');
						});
					});
				}
				else{
					//error
				}
			},{
				scope : 'email , user_likes',
				return_scopes : true
			});
		}
		
		//LOGOUT
		$scope.logout=function(){
			$cookies.remove('cookiename');
			$cookies.remove('cookieimg');
            $cookies.remove('cookieemail')
			$cookies.put('cookiecon',2);
			$scope.mode=$cookies.get('cookiecon');
		}
		
		
		//ABONNEMENR A LA NEWSLETTER
		$scope.abos={};
		$scope.errorreg = false;
		$scope.abonner=function(){
			$scope.EmailCheck($scope.abos.em);
			if($scope.errorEm==1){
				$scope.errorreg = true;
			}
			else
			{
				$scope.exist = false;
				$scope.success=false;
				$http.post('/abonnement?email='+$scope.abos.em)
				.then(function(response){ 
					 if(response.data==false){
						 $scope.exist = true; 
						 $scope.errorreg = false;
						 $scope.success=false;
					 }else{ 
						 $scope.success=true;
						 $scope.exist = false;
						 $scope.errorreg = false;
						 $scope.abos.em = '';
					 }
				  }),
				  function (error){
					console.log(error);
				  };
			}
		}
	
});
