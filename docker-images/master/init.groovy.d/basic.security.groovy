#!groovy

import jenkins.model.*
import hudson.security.*
import hudson.plugins.active_directory.*

def instance = Jenkins.getInstance()
def strategy = instance.getAuthorizationStrategy()
println "Found authorization strategy $strategy"

//only set authorization strategy if no strategy is defined 
if (strategy instanceof hudson.security.AuthorizationStrategy.Unsecured || strategy instanceof hudson.security.FullControlOnceLoggedInAuthorizationStrategy)
{
	def cacheConfig = new hudson.plugins.active_directory.CacheConfiguration(256, 10)
	def adRealm = new hudson.plugins.active_directory.ActiveDirectorySecurityRealm("office.otomato.com",
                  null,"CN=bind ldap, CN=Users, DC=office, DC=otomato, DC=com","abcd1234", "ildcldap.office.otomato.com:3268",
                  hudson.plugins.active_directory.GroupLookupStrategy.AUTO, false, cacheConfig)
	//fix servers allocation
	adRealm.domains[0].servers = "ildcldap.office.otomato.com:3268"
	instance.setSecurityRealm(adRealm)

        strategy = new FullControlOnceLoggedInAuthorizationStrategy()
}

instance.setAuthorizationStrategy(strategy)
instance.save()
