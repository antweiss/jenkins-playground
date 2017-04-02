#!groovy

import jenkins.model.*
import hudson.security.*
import hudson.plugins.active_directory.*

def instance = Jenkins.getInstance()
def strategy = instance.getAuthorizationStrategy()
println "Found authorization strategy $strategy"
//only set strategy to global matrix if no strategy is defined 
if (strategy instanceof hudson.security.AuthorizationStrategy.Unsecured || strategy instanceof hudson.security.FullControlOnceLoggedInAuthorizationStrategy)
{
	println "Unsecured authorization strategy found. Setting to global matrix with predefined list of admins"
	strategy = new hudson.security.GlobalMatrixAuthorizationStrategy()
	instance.setAuthorizationStrategy(strategy)

	def permap = [(hudson.model.Hudson.ADMINISTER):"shalom.k, Jenkins-Admins, liran.s, muhammad.a@otomato.com, anton.w, jenia.g, aviv.s, dave.e, ilan.s, eran.k, maurice.k, avi.l, ben.k, lior.g, alon.p, dba, eyal.c, tidhar.o, yuri.g, amir.s, shlomi.a, alon.g, nitzan.v, omprakash.v, effie.n, tal.s, michael.z, roy.a, yoram.m, dor.t, IT-SecurityGroup, barry.l, idan.cohen, moty.t"]

	println "setting authorization strategy"
	permap.each() { key, value ->
  		names = value.split(',')
  		names.each() { name ->
    
   		  strategy.add(key, name.trim())
 
 	 	}	
 
	}
} //end if 

instance.setAuthorizationStrategy(strategy)
println "Saving the authorization strategy"
instance.save()
