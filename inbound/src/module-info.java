module inbound {
    requires logic;
    requires msal4j;
    requires jdk.httpserver;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.annotation;
    requires microsoft.graph;


    exports OutlookInbound;
}