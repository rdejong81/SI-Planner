package Facade;

import Planning.InboundIAO;

import java.util.Map;

public interface InboundConnectionFactory
{
    Map<String,Integer> getInboundDrivers();
    InboundIAO InboundFactoryCreate(int type);
}
