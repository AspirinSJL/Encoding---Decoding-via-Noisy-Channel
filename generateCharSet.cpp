#include <fstream>
#include <cstdio>

using namespace std;

int main()
{
	freopen("charSet.txt", "w", stdout);

	// output all available char
	for(int i = 0; i < 128; ++i)
	{
		printf("%c", i);
	}

	return 0;
}