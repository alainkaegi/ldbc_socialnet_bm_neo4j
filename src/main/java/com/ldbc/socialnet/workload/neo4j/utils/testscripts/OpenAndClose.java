package com.ldbc.socialnet.workload.neo4j.utils.testscripts;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import com.ldbc.socialnet.workload.neo4j.utils.Config;
import com.ldbc.socialnet.workload.neo4j.utils.GraphUtils;

public class OpenAndClose
{
    private final static Logger logger = Logger.getLogger( OpenAndClose.class );

    public static void main( String[] args ) throws IOException
    {
        OpenAndClose openCloser = new OpenAndClose( Config.DB_DIR );
        openCloser.openAndClose();
    }

    private final String dbDir;

    public OpenAndClose( String dbDir )
    {
        this.dbDir = dbDir;
    }

    public void openAndClose() throws IOException
    {
        logger.info( "Starting Neo4j" );
        GraphDatabaseService db = new GraphDatabaseFactory().newEmbeddedDatabase( new File( dbDir ) );

        logger.info( "Calculating Graph Metrics:" );
        logger.info( "\tNode count = " + GraphUtils.nodeCount( db, 10000000 ) );
        logger.info( "\tRelationship count = " + GraphUtils.relationshipCount( db, 10000000 ) );

        db.shutdown();
    }
}
