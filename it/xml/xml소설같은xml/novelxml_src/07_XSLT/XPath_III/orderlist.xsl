<?xml version="1.0" encoding="euc-kr"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                      version="1.0">
	<xsl:output method="html" />

	<xsl:template match="/">
		<xsl:apply-templates select="order/productlist" />
	</xsl:template>
	
	<xsl:template match="order/productlist">
		<p><xsl:value-of select="product[1]/title/@number" /></p>
		<p>
			<xsl:value-of select="/order/productlist/product[3]/title/@number" />
		</p>
	</xsl:template>
</xsl:stylesheet>
<!-- //
9
1
// -->