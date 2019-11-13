using System;
using System.Collections.Generic;
using System.IO;
using System.Reflection;

namespace Common
{
    public class InputFileReader
    {
        public static List<String> ReadLines(string filename)
        {
            return ReadLines(filename, Convertors.StringToString);
        }

        public static List<T> ReadLines<T>(string filename, Func<string, T> convertor)
        {
            var assembly = Assembly.Load("TestInputs");
            var resourceName = "TestInputs." + filename;

            var result = new List<T>();
            using (Stream stream = assembly.GetManifestResourceStream(resourceName))
            {
                if (stream != null)
                {
                    using StreamReader reader = new StreamReader(stream);

                    var line = reader.ReadLine();
                    while (line != null)
                    {
                        result.Add(convertor.Invoke(line));
                        line = reader.ReadLine();
                    }
                }
                else
                {
                    throw new FileNotFoundException($"Cannot find file: '{filename}'");
                }
            }

            return result;
        }
    }
}