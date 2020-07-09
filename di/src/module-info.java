module di {
    requires dal;
    requires invoice;
    requires logic;
    requires inbound;
    exports Database;
    exports Invoice;
    exports Inbound;
}