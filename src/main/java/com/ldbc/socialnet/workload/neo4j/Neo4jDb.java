package com.ldbc.socialnet.workload.neo4j;

import java.io.File;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ldbc.driver.Db;
import com.ldbc.driver.DbConnectionState;
import com.ldbc.driver.DbException;
import com.ldbc.driver.util.MapUtils;

/**
 * Neo4j client for LDBC DShini workload
 * 
 * Properties to set:
 * 
 * neo4j.url=http://localhost:7474/db/data <br>
 * neo4j.clear=false <br>
 * neo4j.path=/tmp/db <br>
 * neo4j.dbtype=embedded <br>
 */

public class Neo4jDb extends Db
{
    private static Logger logger = Logger.getLogger( Neo4jDb.class );

    private String url;
    private String dbType;
    private String path;

    private Neo4jDbCommands commands;

    @Override
    protected void onInit( Map<String, String> properties ) throws DbException
    {
        // Initialize Neo4j driver
        url = MapUtils.getDefault( properties, "neo4j.url", "http://localhost:7474/db/data" );
        path = MapUtils.getDefault( properties, "neo4j.path", "/tmp/db" );
        dbType = MapUtils.getDefault( properties, "neo4j.dbtype", "embedded" );

        logger.info( "*** Neo4j Properties ***" );
        logger.info( "database type = " + dbType );
        logger.info( "url = " + url );
        logger.info( "path = " + new File( path ).getAbsolutePath() );
        logger.info( "************************" );

        if ( dbType.equals( "server" ) )
        {
            logger.info( "Connecting to database: " + url );
            // TODO implement
            throw new DbException( "server commands not implemented yet" );
        }
        else if ( dbType.equals( "embedded" ) )
        {
            logger.info( "Connecting to database: " + path );
            commands = new Neo4jDbCommandsEmbedded( path );
        }
        else
        {
            logger.error( String.format( "Invalid database type: %s. Must be 'server' or 'embedded'", dbType ) );
        }

        commands.init();
        commands.registerHandlersWithDb( this );
        logger.info( "Initialization complete" );
    }

    @Override
    protected void onCleanup() throws DbException
    {
        try
        {
            commands.cleanUp();
        }
        catch ( Exception e )
        {
            String msg = "Error encountered during cleanup";
            logger.error( msg, e.getCause() );
            throw new DbException( msg, e.getCause() );
        }
    }

    @Override
    protected DbConnectionState getConnectionState() throws DbException
    {
        return commands.getDbConnectionState();
    }
}
