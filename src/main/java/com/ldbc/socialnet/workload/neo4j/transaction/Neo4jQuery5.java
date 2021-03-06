package com.ldbc.socialnet.workload.neo4j.transaction;

import com.ldbc.socialnet.workload.LdbcQuery5;
import com.ldbc.socialnet.workload.LdbcQuery5Result;

public interface Neo4jQuery5 extends Neo4jQuery<LdbcQuery5, LdbcQuery5Result>
{
    /*
    QUERY 5

    Description
        What are the groups that your connections (friendship up to second hop) have joined after a certain date? 
        Order them by the number of posts and comments your connections made there.
    
    PARAMETERS:                
        Person
        Date
    
    RETURN:
        Group
        count
     */

    /*
        cakesAndPies - 2013, Calendar.OCTOBER, 2
            Alex - 2013, Calendar.OCTOBER, 2 
            Aiya - 2013, Calendar.OCTOBER, 3
            Stranger - 2013, Calendar.OCTOBER, 4
            Jake - 2013, Calendar.OCTOBER, 8 

        redditAddicts - 2013, Calendar.OCTOBER, 22
            Jake - 2013, Calendar.OCTOBER, 22

        floatingBoats - 2013, Calendar.NOVEMBER, 13
            Jake - 2013, Calendar.NOVEMBER, 13 
            Alex - 2013, Calendar.NOVEMBER, 14
            Peter -  2013, Calendar.NOVEMBER, 16 

        kiwisSheepAndBungyJumping - 2013, Calendar.NOVEMBER, 1
            Aiya - 2013, Calendar.NOVEMBER, 1
            Alex - 2013, Calendar.NOVEMBER, 4
     */
}
