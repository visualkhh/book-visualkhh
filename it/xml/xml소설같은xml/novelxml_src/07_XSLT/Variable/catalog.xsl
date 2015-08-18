<?xml version="1.0"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                      version="1.0">
	<xsl:output method="html" />
	<xsl:variable name="total" select="sum(//Price)" />

	<xsl:template match="/">
		<xsl:variable name="count" select="count(//Book)" />
		<xsl:value-of select="$count" />
		Books Total Price : 
		<xsl:value-of select="$total" />
	</xsl:template>
</xsl:stylesheet>
<!-- //
3 Books Total Price : 108.97
// -->