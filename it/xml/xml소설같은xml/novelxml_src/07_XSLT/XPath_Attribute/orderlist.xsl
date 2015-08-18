<?xml version="1.0" encoding="euc-kr"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

	<xsl:output method="html" />

	<xsl:template match="/">
		<HTML>
			<HEAD>
				<TITLE>Book Catalog</TITLE>
			</HEAD>
			<BODY>
				<xsl:apply-templates select="orderlist/order/itemlist" />
			</BODY>
		</HTML>
	</xsl:template>
	
	<xsl:template match="orderlist/order/itemlist">
		<p><xsl:value-of select="item[1]/title/@number" /></p>
	</xsl:template>
</xsl:stylesheet>